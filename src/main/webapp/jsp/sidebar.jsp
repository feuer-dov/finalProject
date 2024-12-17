<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class='sidebar'>
	<form action='home'>
		<input type='submit' value='All' />
	</form>
	<h3>Categories</h3>
	<c:forEach var="cat" items="${categories }">
		<form action='home'>
			<input type='submit' name='category' value='${cat }' />
		</form>
	</c:forEach>
	<h3>Brands</h3>
	<c:forEach var="brand" items="${brands }">
		<form action='home'>
			<input type='submit' name='brand' value='${brand }' />
		</form>
	</c:forEach>
</div>