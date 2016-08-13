<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 
	<p>Hello</p>
	<form action="get-user-by-id.html" method="POST">
		<input type="text" name="_id">
		<input type="submit" value="submit">
	</form>
 -->
 
<c:url var="firstUrl" value="/home.html?p=1" />
<c:url var="lastUrl" value="/home.html?p=${page.totalPages}" />
<c:url var="prevUrl" value="/home.html?p=${currentIndex - 1}" />
<c:url var="nextUrl" value="/home.html?p=${currentIndex + 1}" />


                    <!-- *** LEFT COLUMN ***
			 _________________________________________________________ -->

                    <div class="col-md-9" id="blog-listing-medium">

				<c:set var="list" value="${page.content}"></c:set>
				<c:forEach var="article" items="${list}">
					
						<section class="post">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="image">
                                        <a href="blog-post.html?a=${article._id}">
                                       	<c:choose>
                                       		<c:when test="${article.featuredImage == '0'}">
                                       		<img src="resources/front/img/default-featuredImage.jpg" class="img-responsive" alt="Example blog post alt">
                                       		</c:when>
                                       		<c:otherwise>
                                       		<img src="${article.featuredImage}" class="img-responsive" alt="Example blog post alt">
                                       		</c:otherwise>
                                       	</c:choose>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <h2><a href="blog-post.html?a=${article.urlTitle}"><c:out value="${article.title}"/></a></h2>
                                    <div class="clearfix">
                                        <p class="author-category">By <a href="user-info.html?n=${article.user.name}"><c:out value="${article.user.name}"/></a> in 
                                        	<a href="blog-topic.html?tp=${article.topic.urlName}"><c:out value="${article.topic.name}"/></a>
                                        </p>
                                        <p class="date-comments">
                                            <a href="blog-post.html"><i class="fa fa-calendar-o"></i>
                                            	<fmt:formatDate value="${article.date}" var="dateCreated" pattern="dd-MM-yyyy HH:mm:ss"/>
                                            	<c:out value="${dateCreated}"/>
                                            </a>
                                            <a href="blog-post.html"><i class="fa fa-comment-o"></i> <c:out value="${countComment[article._id]}" /> Comments</a>
                                        </p>
                                    </div>
                                    <p class="intro"><c:out value="${article.excerpt}"/></p>
                                    <p class="read-more"><a href="blog-post.html" class="btn btn-template-main">Continue reading</a>
                                    </p>
                                </div>
                            </div>
                        </section>
					
				</c:forEach>

						<!-- 
                        <ul class="pager">
                            <li class="previous"><a href="#">&larr; Older</a>
                            </li>
                            <li class="next disabled"><a href="#">Newer &rarr;</a>
                            </li>
                        </ul>
 						-->


                        <div class="pages">
							<!--  
                            <p class="loadMore">
                                <a href="#" class="btn btn-template-main"><i class="fa fa-chevron-down"></i> Load more</a>
                            </p>
                            -->

							<ul class="pagination">
						<c:choose>
							<c:when test="${currentIndex == 1}">
								<li class=""><a href="#">&laquo;</a></li>
								<li class=""><a href="#">&lt;</a></li>
							</c:when>
							<c:otherwise>
				                <li><a href="${firstUrl}">&lt;&lt;</a></li>
				                <li><a href="${prevUrl}">&lt;</a></li>
            				</c:otherwise>
						</c:choose>
					<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
						<c:url var="pageUrl" value="home.html?p=${i}"/>
						<c:choose>
							<c:when test="${i == currentIndex}">
								<li class="active"><a href="${pageUrl}"> <c:out value="${i}"/></a>
                               	</li>
							</c:when>
							<c:otherwise>
								<li class=""><a href="${pageUrl}"> <c:out value="${i}"/></a>
                               	</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						<c:choose>
							<c:when test="${currentIndex == page.totalPages}">
								<li><a href="#">&gt;</a></li>
								<li><a href="#">&raquo;</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${nextUrl}">&gt;</a></li>
								<li><a href="${lastUrl}">&raquo;</a></li>
							</c:otherwise>
						</c:choose>
                                
                            </ul>
                        </div>


                    </div>
                    <!-- /.col-md-9 -->
                    <!-- *** LEFT COLUMN END *** -->




