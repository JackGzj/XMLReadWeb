<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/XMLReadWeb/jquery-1.8.0.js"></script>
<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="/XMLReadWeb/datatables.css"/>
<script type="text/javascript" src="/XMLReadWeb/datatables.js"></script>
<head>
<title>小瓜子的XML实验网页</title>
</head>
<body>
<script type="text/javascript">
	
	$(document).ready(function() {
		var url = "/XMLReadWeb/city/getData"
		$('#example').dataTable( {
			 // "serverSide":true,
			  "ajax": {
				  "url": url,
				  "data" : {"test" : "  *****Jack！！！"},
				  /*"dataSrc":function(json){
					  updateChart(window.myChart,json.data);
					  return json.data;
				  } */
			  },
			  "columns":[
			      {"data": "ID"},
			      {"data": "Name"},
			      {"data": "CountryCode"},
			      {"data": "District"},
			      {"data": "Population"}
			      ],
			//  "columnDefs":[
				//  {targets:0,visible:false}
			  //],
			  "rowId":"ID",
			  "select":{
				  "style":"multi"
			  },
			  "dom": 'lfrtip'
		} );
	} );
	
	</script>

    <h1 style="margin-bottom: 10px" align="center">XML读取测试</h1>
	<table id="example" class="display">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>CountryCode</th>
				<th>District</th>
				<th>Population</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>CountryCode</th>
				<th>District</th>
				<th>Population</th>
			</tr>
		</tbody> 
	</table>
	
	<a href="/XMLReadWeb/city/getRaw"  target=_blank style="font-size: 20px;margin-top: 40px;margin-left: 20px">查看XML源文件</a>
	
	
</body>
</html>