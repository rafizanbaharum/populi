<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
</head>
<body>
<div>

    <ol>
        <c:forEach var="district" items="${districts}">
            <li><a href="${pageContext.request.contextPath}/district/navigate/${district.id}">
                    ${district.description}
            </a></li>
        </c:forEach>
    </ol>
</div>
</body>
</html>

