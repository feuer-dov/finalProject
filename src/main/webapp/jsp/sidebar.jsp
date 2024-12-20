<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class='sidebar'>
	<form action='home'>
		<input type='submit' value='All (A-Z)' />
	</form>
	<form action='home'>
		<input type='submit' value='All (By Price Ascending)' />
		<input type='hidden' name='byPrice' value='asc'/>
	</form>
	<form action='home'>
		<input type='submit' value='All (By Price Descending)' />
		<input type='hidden' name='byPrice' value='desc'/>
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