<%--
  Created by IntelliJ IDEA.
  User: Laptop
  Date: 3/15/2022
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <title>Title</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
            crossorigin="anonymous" referrerpolicy="no-referrer"/>
  </head>
  <body>
<h1>Danh sách sản phẩm</h1>
<a href="/products?action=create">Tạo mới sản phẩm</a>
<form action="/products">
<input type="text" name="q" placeholder="Tên sản phẩm cần tìm">
<button type="submit" style="background: lightskyblue">Tìm kiếm</button>

<table class="table table-bordered">
<thead>
<div>
    <tr>
        <th scope="col">Mã sản phẩm</th>
        <th scope="col">Tên sản phẩm</th>
        <th scope="col">Giá sản phẩm</th>
        <th scope="col">Số lượng</th>
        <th scope="col">Màu</th>
        <th scope="col">Mô tả</th>
        <th scope="col">Danh mục</th>
        <th colspan="2">lựa chọn</th>
    </tr>

</div>

</thead>
<c:forEach var="product" items="${products}">
    <tr>
        <th scope="row">${product.id}</th>
        <td><a href="/products?action=view&id=${product.id}">${product.name}</a></td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
        <td>${product.color}</td>
        <td>${product.description}</td>
        <td>${product.category}</td>
        <td><a href="/products?action=edit&id=${product.id}" class="btn btn-primary"><i
                class="fas fa-edit"></i></a></td>
        <td><a href="/products?action=delete&id=${product.id}" class="btn btn-danger"><i
                class="fas fa-trash"></i></a></td>
    </tr>
</c:forEach>
<tbody>
</table>
</form>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
          crossorigin="anonymous"></script>
  </body>
</html>
