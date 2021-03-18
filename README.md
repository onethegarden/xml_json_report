## xml, json으로 보고서 만들기



- spring 을 이용하여 간단한 rest 프로젝트를 만들고 xml, json으로 리턴하여 보고서 각각 html로 출력하기 위한 레파지토리
- xml과 json의 차이를 이해하고자 한다.
- 확실히 구현할 때 xml은 해줘야 되는 게 많아서(vo 어노테이션 설정, 프론트단에서 응답 받은 후 파싱, 노드를 하나하나 구분하여 데이터 꺼내오기) 번거롭다. 
- xml을 웹에서는 많이 안쓴다고 하는데, xml이 스키마를 지정할 수 있어 데이터의 무결성을 검증할 수 있다고 한다. (물론 json도 스키마가 있다,, 하지만 아직 draft단계로 많이 안쓴다고 함....)
- https://www.coovil.net/xml-vs-json/  : xml과 json에 대해 읽어보면 좋은 글이다.



<br/>

#### 💻home.jsp 화면



![image](https://user-images.githubusercontent.com/51187540/111591368-b143e780-880a-11eb-833b-82d88c8b4e36.png)

<br/><br/>

#### 1. XML, JSON의 차이

| **JSON**                                      | **XML**                                                      |
| :-------------------------------------------- | :----------------------------------------------------------- |
| JSON object has a type                        | XML data is typeless                                         |
| Data is readily accessible as JSON objects    | XML data needs to be parsed.                                 |
| JSON is supported by most browsers.           | Cross-browser XML parsing can be tricky                      |
| JSON supports only text and number data type. | XML support various data types such as number, text, images, charts, graphs, etc. It also provides options for transferring the structure or format of the data with actual data. |
| Retrieving value is easy                      | Retrieving value is difficult                                |
| Supported by many Ajax toolkit                | Not fully supported by Ajax toolkit                          |

출처 : https://www.guru99.com/json-vs-xml-difference.html



<br/>

<br/>

#### 2. spring을 이용하여 간단한 REST 구현

- Controller 단에서 간단한 테스트 VO를 리턴하여 html로 출력하는 것까지 구현하였다.

  (공공기관 api에서 xml로 데이터를 넘기는 경우가 많다는데, 받는 것을 구현해 보는 것도 좋을 것 같다)

- 파일구조

![image](https://user-images.githubusercontent.com/51187540/111592227-dd139d00-880b-11eb-8d7b-f5c7ad823b29.png)





- ```/myapp/rest/testXML``` 로 호출 시

  ![image](https://user-images.githubusercontent.com/51187540/111593083-ea7d5700-880c-11eb-8966-9d96868d9a26.png)



- ```/myapp/rest/testXML``` 로 호출 시

  ![image](https://user-images.githubusercontent.com/51187540/111592998-d3d70000-880c-11eb-97e5-c233f2f4a99f.png)

<br/>

<br/>

 #### 3. XML과 JSON VO차이

- 일단 둘 다 저렇게 여러개의 깊이로 만드려면 깊이 당 객체 하나가 있어야 한다.  ```contents```처럼 반복되는 게 필요하다면 리스트로 받아야 한다
- xml -
  - certificate의 자녀객체는 header와 article 두 개인데 편의상 header 객체까지만 쓰고 나머지는 생략하였다.
  - @XmlRootElemen , @XmlElement를 사용하면 된다. 
  - @XmlType 이거는 안써도 되긴 하는데, 태그 순서를 설정하기 위해 사용하였다. (안쓰면 순서가 알파벳 순으로 되는듯 하다)

```java
@XmlRootElement(name = "certificate") //루트태그 설정
@XmlType(propOrder = { "header", "article" }) //태그의 순서 설정
public class TestXMLVO {
	private Header header;
	private Article article;

	public TestXMLVO() {

	}

	public TestXMLVO(Header header, Article article) {
		super();
		this.header = header;
		this.article = article;
	}

	@XmlElement(name = "header") //태그를 명시
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@XmlElement(name = "article")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	/* Declare Header tag */
	@XmlRootElement(name = "header")
	public static class Header implements Serializable {
		private String certknd;
		private String certfg;
		private String department;

		public Header() {
		}

		public Header(String certknd, String certfg, String department) {
			super();
			this.certknd = certknd;
			this.certfg = certfg;
			this.department = department;
		}

		@XmlElement(name = "certknd")
		public String getCertknd() {
			return certknd;
		}

		public void setCertknd(String certknd) {
			this.certknd = certknd;
		}

		@XmlElement(name = "certfg")
		public String getCertfg() {
			return certfg;
		}

		public void setCertfg(String certfg) {
			this.certfg = certfg;
		}

		@XmlElement(name = "department")
		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		
	}

```

- json
  - 일반 vo와 같지만 저 xml과 맞춰서 내보내려 해서 깊이가 있는게 약간 다르다

```java
public class TestVO {
	private Header header;
	private Article article;

	public TestVO() {

	}

	public TestVO(Header header, Article article) {
		super();
		this.header = header;
		this.article = article;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}


	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}


	public static class Header implements Serializable {
		private String certknd;
		private String certfg;
		private String department;

		public Header() {
		}

		public Header(String certknd, String certfg, String department) {
			super();
			this.certknd = certknd;
			this.certfg = certfg;
			this.department = department;
		}

		public String getCertknd() {
			return certknd;
		}

		public void setCertknd(String certknd) {
			this.certknd = certknd;
		}

		public String getCertfg() {
			return certfg;
		}

		public void setCertfg(String certfg) {
			this.certfg = certfg;
		}

		public String getDepartment() {
			return department;
		}

		public void setIrofcNm(String department) {
			this.department = department;
		}

	}
```



<br/>

<br/>



#### XML, JSON 파싱하여 보여주기

- xml

  - 일단 response.json()과 같은 xml()이런게 없다. response.text()로 받아서 파싱해줘야 한다.

  ```javascript
  const requestXML = async (url) => {
    const response = await fetch(url);
    if (response.ok) {
      return await response.text(); //요청 보내고 body를 text로 변환
    } else {
      throw new Error(response.status);
    }
  };
  ```

  

  - 이렇게 요소 하나하나 지정해서 파싱을 해준다.

  ```javascript
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
  ```

  - 파싱한 걸 html에 붙여주면 된다.

  ```javascript
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
  ```



<br/><br/><br/>

- json

  - 일단 response.json()이 돼서 따로 파싱할 필요가 없다.

  ```javascript
  const requestJSON = async (url) => {
    const response = await fetch(url);
    if (response.ok) {
      return await response.json();
    } else {
      throw new Error(response.status);
    }
  };
  ```

  - 간단히 받아서 json 깊이에 맞게 뿌려주면 된다.

  ```javascript
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
  ```

  