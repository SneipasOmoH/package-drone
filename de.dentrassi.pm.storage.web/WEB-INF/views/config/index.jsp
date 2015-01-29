<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib tagdir="/WEB-INF/tags/main" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://dentrassi.de/osgi/web/form" prefix="form" %>

<h:main title="Setup">

<div class="container-fluid ">

<div class="row">

<div class="col-sm-8">

<c:if test="${empty jdbcDrivers}">
<div class="warning">
	<div class="title">No JDBC drivers were found.</div>
	<div>You need to install some OSGi compatible JDBC drivers!</div>
</div>
</c:if>

<form:form action="" method="POST"  cssClass="form-horizontal">

	<h:formEntry label="JDBC Driver"  command="command" path="jdbcDriver">
	    <form:select path="jdbcDriver" cssClass="form-control">
	        <form:option value="" label="Choose JDBC Driver"/>
	        <form:optionList items="${jdbcDrivers }" itemValue="className"/>
	    </form:select>
	</h:formEntry>

    <h:formEntry label="URL"  command="command" path="url">
        <form:input path="url" cssClass="form-control"/>
    </h:formEntry>
    
    <h:formEntry label="User"  command="command" path="user">
        <form:input path="user" cssClass="form-control"/>
    </h:formEntry>
    
    <h:formEntry label="Password"  command="command" path="password">
        <form:input path="password" cssClass="form-control"/>
    </h:formEntry>
    
	<div class='form-group ${form:validationState(pageContext,"command", "additionalProperties", "", "has-error")}'>
		<form:label path="additionalProperties" cssClass="col-sm-2 control-label">Additional Properties:</form:label>
		<div class="col-sm-10">
		  <form:textarea path="additionalProperties" cols="40" rows="10" cssClass="form-control"/>
		</div>
		<div class="col-sm-10 col-sm-offset-2">
            <form:errorList path="additionalProperties" cssClass="help-block" />
        </div>
	</div>

    <input type="submit" value="Submit" class="btn btn-primary">
    <input type="reset" value="Reset" class="btn btn-default">

</form:form>

</div>

<div class="col-sm-4">

<div class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">Service status</h3>
  </div>
  <div class="panel-body">

<table class="table">

<tr><th>Database schema version</th><td>${databaseSchemaVersion }</td></tr>
<tr><th>Current schema version</th><td>${currentVersion }</td></tr>
<tr><th>Storage service present</th><td id="storage-service-present">${storageServicePresent }</td></tr>

</table>

<p>

<c:if test="${ configured && ( empty databaseSchemaVersion || currentVersion > databaseSchemaVersion ) }">
<div>
	<form method="post" action="<c:url value="/config/databaseUpgrade" />">
	   <c:choose>
	       <c:when test="${empty databaseSchemaVersion }">
       <input type="submit" value="Install Schema" class="btn btn-primary" id="install-schema"/>
	       </c:when>
	       <c:otherwise>
        <input type="submit" value="Upgrade Schema" class="btn btn-primary" id="upgrade-schema" />
	       </c:otherwise>
	   </c:choose>
	   
    </form>
</div>
</c:if>

  </div>
</div>

</div> <%-- col --%>

</div> <%-- row --%>

</div> <%-- container --%>


</h:main>