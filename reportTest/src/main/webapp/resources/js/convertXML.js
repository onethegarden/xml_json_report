const requestXML = async (url) => {
  const response = await fetch(url);
  if (response.ok) {
    return await response.text();
  } else {
    throw new Error(response.status);
  }
};

const getXMLData = async () => {
  const response = await requestXML(`http://localhost:8080/myapp/rest/testXML`);
  document.querySelector(".setXML").innerHTML = response;

  const xmlWrapper = document.querySelector(".xmlWrapper");
  const reportButton = document.createElement("button");
  reportButton.innerText = "convertReport";
  xmlWrapper.appendChild(reportButton);
  reportButton.addEventListener("click", () => {
    setXMLReport(response);
  });
};

const setXMLReport = (xmlText) => {
  parser = new DOMParser();
  xmlDoc = parser.parseFromString(xmlText, "text/xml");

  const certknd = xmlDoc.getElementsByTagName("certknd")[0].innerHTML;
  const certfg = xmlDoc.getElementsByTagName("certfg")[0].innerHTML;
  const department = xmlDoc.getElementsByTagName("department")[0].innerHTML;
  const article = xmlDoc.getElementsByTagName("article")[0].children;

  let article_fg = "";
  let article_contents = [];

  Array.prototype.forEach.call(article, (elem) => {
    if (elem.nodeName == "contents") {
      let content = { fg: "", val: "" };
      Array.prototype.forEach.call(elem.children, (child) => {
        if (child.nodeName == "fg") {
          content.fg = child.innerHTML;
        } else {
          content.val = child.innerHTML;
        }
      });

      article_contents.push(content);
    } else if (elem.nodeName == "fg") {
      article_fg = elem.innerHTML;
    }
  });

  const innerHtml = `
        <div class="header">
              <h2>${certknd}</h2>
              <h4>${certfg}</h4>
            </div>
            <table>
                <tr>
                    <td width="100px" class="center">학과</td>
                    <td>${department}</td>
                </tr>
            </table>
            <table style="margin-top:10px;">
          <tr>
          <td width="100px" class="center">구분</td>
              <td class="center">상세내용</td>
          </tr>
          <tr>
              <td class="center">
                  ${article_fg}
              </td>
              <td>
                  ${article_contents
                    .map((v) => {
                      return `[${v.fg}] ${v.val} <br/>`;
                    })
                    .join("")}
              </td>
          </tr>
      </table>
        `;

  const reportApp = document.querySelector(".reportApp");
  reportApp.innerHTML = innerHtml;
};
