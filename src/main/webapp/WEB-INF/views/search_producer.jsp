<%@page import="com.study.dvd.entity.Producer"%>
<%@page import="java.util.List"%>
<%@page import="com.study.dvd.dao.ProducerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	body {
		overflow : auto;
	}
	
	table, th, td {
		border: 1px solid #dbdbdb;
		border-collapse: collapse;
	}
	
	th, td{
		padding: 5px 10px;
	}
</style>

</head>
<body>
	<div>
		<label>Producer 검색</label>
		<input type="text"
			class="producer-input"
			placeholder="Producer 이름을 입력하세요">
		<button onclick="handleProducerClick()">검색</button>			
	</div>
	<% 
		String searchText = request.getParameter("searchText"); // input 에서 입력한 값을 가지고
		List<Producer> producers = ProducerDao.searchProducerByProducerName(searchText); //sql로 일치하는 제작자를 찾는다
	%>
	<table>
		<thead>
			<tr>
				<th>PRODUCER_ID</th>
				<th>제작자</th>
			</tr>
		</thead>
		<tbody>
			<% 
				for(Producer producer : producers) {
				//producers.forEach(producer -> { 도 가능한데 jsp 에서는 람다식 안ㅁ넉음!!
			%>
			
				<tr>
					<td><%=producer.getProducerId() %></td>
					<td><%=producer.getProducerName() %></td>
				</tr>
			
			<% 
				}
				//});
			%>
		</tbody>
	</table>
	
	<script src="/dvd/static/search_producer.js"></script>

</body>
</html>