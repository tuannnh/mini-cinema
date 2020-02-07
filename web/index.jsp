<%-- 
    Document   : index
    Created on : Feb 2, 2020, 3:17:58 PM
    Author     : tuannnh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/favicon.png">
        <link rel="icon" type="image/png" href="assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>
            Bi's Cinema
        </title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
              name='viewport' />
        <!--     Fonts and icons     -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
        <!-- CSS Files -->
        <link href="assets/demo/demo.css" rel="stylesheet" />

        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/paper-kit.css?v=2.3.0" rel="stylesheet" />
        <!-- CSS Just for demo purpose, don't include it in your project -->
    </head>
    <body class="search-page sidebar-collapse">
        <!-- Navbar -->

        <nav class="navbar navbar-expand-lg fixed-top bg-danger nav-down" color-on-scroll="500">
            <div class="container">
                <div class="navbar-translate">
                    <a class="navbar-brand" href="ListMovie" rel="tooltip"
                       title="Bi's Cinema" data-placement="bottom">
                        Bi's Cinema
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar bar1"></span>
                        <span class="navbar-toggler-bar bar2"></span>
                        <span class="navbar-toggler-bar bar3"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" data-nav-image="../assets/img/blurred-image-1.jpg" data-color="orange">
                    <ul class="navbar-nav ml-auto">

                        <li class="nav-item">
                            <button type="button" class="btn btn-danger btn-round" data-toggle="modal"
                                    data-target="#new-movie">New Movie</button>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>

        <div id="new-movie" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content ">

                    <div class="container pt-4 pr-4 pl-4 pb-4">
                        <form action="AddMovie" method="POST" class="login-form ">
                            <label>Title</label>
                            <input name="title" type="text" class="form-control" placeholder="Title"><br/>
                            <label>Image URL:</label>
                            <input name="image" type="text" class="form-control" placeholder="https://"><br/>
                            <label>Movie Direct Link</label>
                            <input name="link" type="text" class="form-control " placeholder="https://"><br/>
                            <input class="btn btn-danger btn-lg btn-round" type="submit" name="action" value="New">
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <!-- End Navbar -->


        <div class="wrapper">

            <div class="main">

                <div class="section section-white">
                    <div class="container">

                        <form action="SearchMovie" method="POST" class="form-inline search-form">
                            <div class="input-group no-border">
                                <span class="input-group-addon addon-xtreme no-border" id="basic-addon1"><i class="fa fa-search"></i></span>
                                <input type="text" name="search" class="form-control input-xtreme no-border" value="${requestScope.SEARCH}" placeholder="Search movie" aria-describedby="basic-addon1">
                            </div>
                        </form>


                        <div class="row">
                            <div class="col-md-6 ml-auto mr-auto text-center title">
                                <h2>Choose movie</h2>
                            </div>
                        </div>

                        <div class="article"> 
                            <div class="row">
                                <c:if test="${empty requestScope.LIST}">
                                    <div class="col-md-6 ml-auto mr-auto text-center title">
                                        <h2>No result</h2>
                                    </div>
                                </c:if>
                                <c:forEach items="${requestScope.LIST}" var="movie" >

                                    <div class="col-md-3">
                                        <div class="card card-blog card-plain text-center">
                                            <div class="card-image">

                                                <img class="img img-raised" src="${movie.image}">

                                            </div>
                                            <div class="card-body">

                                                <span class="card-title">${movie.title}</span>

                                            </div>

                                            <!-- Large modal -->
                                            <button type="button" class="btn btn-primary btn-round btn-block" data-backdrop="true" data-keyboard="true" data-toggle="modal"
                                                    data-target="#movieID-${movie.id}"><i class="fa fa-eye"></i></button>

                                            <div id="movieID-${movie.id}" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
                                                <div class="modal-dialog wide-screen">
                                                    <div class="modal-content wide-screen">
                                                        <iframe src="${movie.link}"
                                                                allowfullscreen></iframe>
                                                    </div>
                                                </div>
                                            </div>
                                  
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                        <hr>
                        <br />
                        <br />


                    </div>
                </div>
            </div>
        </div>



        <footer class="footer  footer-gray footer-white ">
            <div class="container">
                <div class="row">

                    <div class="credits ml-auto">
                        <span class="copyright">
                            ©
                            <script>
                                document.write(new Date().getFullYear())
                            </script>, made with <i class="fa fa-heart heart"></i>by Hung Tuan
                        </span>
                    </div>
                </div>
            </div>
        </footer>
        <!--   Core JS Files   -->
        <script src="assets/js/core/jquery.min.js" type="text/javascript"></script>
        <script src="assets/js/core/popper.min.js" type="text/javascript"></script>
        <script src="assets/js/core/bootstrap.min.js" type="text/javascript"></script>

        <!--  Vertical nav - dots -->
        <!--  Photoswipe files -->
        <script src="assets/js/plugins/photo_swipe/photoswipe.min.js"></script>
        <script src="assets/js/plugins/photo_swipe/photoswipe-ui-default.min.js"></script>
        <script src="assets/js/plugins/photo_swipe/init-gallery.js"></script>
        <!--  for Jasny fileupload -->
        <script src="assets/js/plugins/jasny-bootstrap.min.js"></script>
        <!-- Control Center for Paper Kit: parallax effects, scripts for the example pages etc -->
        <script src="assets/js/paper-kit.js?v=2.3.0" type="text/javascript"></script>

        <!--  Plugin for presentation page - isometric cards  -->
        <script src="assets/js/plugins/presentation-page/main.js"></script>
    </body>
</html>
