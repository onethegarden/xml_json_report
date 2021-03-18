const requestJSON = async (url) => {
  const response = await fetch(url);
  if (response.ok) {
    return await response.json();
  } else {
    throw new Error(response.status);
  }
};

const getJSONData = async () => {
  const response = await requestJSON(
    `http://localhost:8080/myapp/rest/testJSON`
  );
  document.querySelector(".setJSON").innerHTML = JSON.stringify(response);
  const jsonWrapper = document.querySelector(".jsonWrapper");
  const reportButton = document.createElement("button");
  reportButton.innerText = "convertReport";
  jsonWrapper.appendChild(reportButton);
  reportButton.addEventListener("click", () => {
    setJSONReport(response);
  });
};

const setJSONReport = (json) => {
  const JsonData = json;
  const header = JsonData.header;
  const article = JsonData.article;

  const innerHtml = `
      <div class="header">
              <h2>${header.certknd}</h2>
              <h4>${header.certfg}</h4>
          </div>
          <table>
              <tr>
                  <td width="100px" class="center">학과</td>
                  <td>${header.department}</td>
              </tr>
          </table>
          <table style="margin-top:10px;">
    	<tr>
        <td width="100px" class="center">구분</td>
    		<td class="center">상세내용</td>
    	</tr>
    	<tr>
    		<td class="center">
                ${article.fg}
            </td>
    		<td>
                ${article.contents
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
