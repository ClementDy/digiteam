[1mdiff --git a/demo/src/main/java/glp/digiteam/controller/StudentController.java b/demo/src/main/java/glp/digiteam/controller/StudentController.java[m
[1mindex 34aa0ac..ffa352d 100644[m
[1m--- a/demo/src/main/java/glp/digiteam/controller/StudentController.java[m
[1m+++ b/demo/src/main/java/glp/digiteam/controller/StudentController.java[m
[36m@@ -2,6 +2,8 @@[m [mpackage glp.digiteam.controller;[m
 [m
 import java.util.List;[m
 [m
[32m+[m[32mimport javax.servlet.ServletRequest;[m
[32m+[m[32mimport javax.servlet.http.HttpServletResponse;[m
 import javax.servlet.http.HttpSession;[m
 [m
 import org.springframework.beans.factory.annotation.Autowired;[m
[36m@@ -258,13 +260,35 @@[m [mpublic class StudentController {[m
 	[m
 	[m
 	@RequestMapping(value = "/deconnexionStudent", method = RequestMethod.GET)[m
[31m-	public ModelAndView deconnexion(Model model, HttpSession session) {[m
[32m+[m	[32mpublic ModelAndView deconnexion(Model model, HttpSession session,final ServletRequest servletRequest, HttpServletResponse response) {[m
 		if (session.getAttribute("student") == null) {[m
 			return new ModelAndView("redirect:authentication");[m
 		}[m
 [m
 		this.student = null;[m
[32m+[m[32m/*session.invalidate();[m
[32m+[m[41m		[m
[32m+[m		[32mfinal HttpServletRequest request = (HttpServletRequest) servletRequest;[m
[32m+[m		[32msession=request.getSession();[m[41m  [m
[32m+[m		[32mrequest.getSession(false);[m
[32m+[m[32m        session.invalidate();[m
[32m+[m[41m        [m
[32m+[m[32m/*AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();[m
[32m+[m[41m		[m
[32m+[m		[32mMap<String,Object> attributes = principal.getAttributes();[m
[32m+[m[41m		 [m
[32m+[m		[32mIterator attributeNames = attributes.keySet().iterator();[m
[32m+[m[41m		 [m
[32m+[m[41m		[m
[32m+[m[41m		 [m
[32m+[m		[32mwhile( attributeNames.hasNext()) {[m
 	[m
[32m+[m		[32mString attributeName = (String) attributeNames.next();[m
[32m+[m		[32mSystem.out.print(attributeName+ "2 : ");[m
[32m+[m		[32m       Object attributeValue = attributes.get(attributeName);[m
[32m+[m		[32m      System.out.println(attributeValue);[m
[32m+[m[41m		    [m
[32m+[m		[32m}*/[m
 		return new ModelAndView("redirect:authentication");[m
 	}[m
 	[m
