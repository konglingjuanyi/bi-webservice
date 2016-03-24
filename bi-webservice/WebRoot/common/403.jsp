<%@include file="/common/common.jspi"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jspi"%>
<link type="text/css" rel="stylesheet" href="<c:out value="${contextPath}"/>/css/exception.css" media="screen" />
<title>access denied</title>
</head>
<body>
    <div style="width:900px;margin:15px 15px 15px 15px;background:#FFF;padding:5px;overflow:hidden;"> 
      <table width="90%" border="0" align="center"> 
        <tr style="font-size:14pt;font-weight:bold;color:#6293BB;"> 
          <td align="center"></td> 
          <td height="68"  align="left" > 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h1>The request page access has been denied.</h1> 
          </td> 
        </tr> 
        <tr style="font-size:12pt;font-weight:normal;color:#6293BB;"> 
          <td width="22%" align="right"></td> 
          <td width="71%" align="center"> 
            <div style="padding:10px 10px"> 
              <p> 
                <a href="<c:out value="${contextPath}" />" style="font-size:12pt;font-weight:normal;color:#6293BB;" target="_top">Back to homepage</a> 
              </p> 
            </div> 
          </td> 
        </tr> 
      </table> 
    </div> 
</body>
</html>