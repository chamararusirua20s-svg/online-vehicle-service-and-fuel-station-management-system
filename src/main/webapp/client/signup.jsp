<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>AutoCare | User Register</title>
  <link rel="shortcut icon" href="https://ui-avatars.com/api/?name=Auto%20Care&background=0f172a&color=22c55e&rounded=true" type="image/x-icon">
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body class="bg-slate-900 flex items-center justify-center min-h-screen px-4">

  <div class="w-full max-w-xl my-16 p-8 space-y-6 bg-slate-800 shadow-xl rounded-3xl">
    <h2 class="text-4xl font-extrabold text-center text-green-400">User Registration</h2>
    <p class="text-center text-gray-400 text-sm mb-6">Create your AutoCare account</p>

    <!-- Flash Message -->
    <%
      String success = (String) request.getAttribute("success");
      String error = (String) request.getAttribute("error");
    %>
    <c:if test="${not empty success}">
      <div class="w-full bg-green-600/20 border border-green-600 text-green-400 px-4 py-3 rounded-lg text-center text-sm">
        <i class="fas fa-check-circle mr-2"></i> <%= success %>
      </div>
    </c:if>
    <c:if test="${not empty error}">
      <div class="w-full bg-red-600/20 border border-red-600 text-red-400 px-4 py-3 rounded-lg text-center text-sm">
        <i class="fas fa-exclamation-circle mr-2"></i> <%= error %>
      </div>
    </c:if>

    <!-- Form -->
    <form action="${pageContext.request.contextPath}/client/register" method="POST" class="space-y-5">

      <!-- Full Name -->
      <div class="relative">
        <label class="block mb-1 text-gray-300 font-medium">Full Name</label>
        <input type="text" name="full_name" required
               class="w-full px-4 py-2 rounded-lg bg-slate-700 border border-slate-600 text-white focus:ring-2 focus:ring-green-500 focus:outline-none"
               placeholder="Enter your full name">
      </div>

      <!-- Email -->
      <div class="relative">
        <label class="block mb-1 text-gray-300 font-medium">Email</label>
        <input type="email" name="email" required
               class="w-full px-4 py-2 rounded-lg bg-slate-700 border border-slate-600 text-white focus:ring-2 focus:ring-green-500 focus:outline-none"
               placeholder="Enter your email">
      </div>

      <!-- Phone -->
      <div class="relative">
        <label class="block mb-1 text-gray-300 font-medium">Phone</label>
        <input type="number" name="phone" required
               class="w-full px-4 py-2 rounded-lg bg-slate-700 border border-slate-600 text-white focus:ring-2 focus:ring-green-500 focus:outline-none"
               placeholder="Enter your phone number">
      </div>

      <!-- Password -->
      <div class="relative">
        <label class="block mb-1 text-gray-300 font-medium">Password</label>
        <input type="password" name="password" id="password" required
               class="w-full px-4 py-2 rounded-lg bg-slate-700 border border-slate-600 text-white focus:ring-2 focus:ring-green-500 focus:outline-none"
               placeholder="Enter your password">
      </div>

      <!-- Password Hints -->
      <div>
        <p class="text-sm text-gray-400 mb-1">Password must meet the following:</p>
        <ul class="text-sm space-y-1 pl-5 list-disc">
          <li id="lengthHint" class="text-red-600">At least 8 characters</li>
          <li id="uppercaseHint" class="text-red-600">At least one uppercase letter</li>
          <li id="lowercaseHint" class="text-red-600">At least one lowercase letter</li>
          <li id="digitHint" class="text-red-600">At least one number</li>
        </ul>
      </div>

      <!-- Register Button -->
      <div>
        <button type="submit"
                class="w-full px-4 py-2 text-white bg-green-600 hover:bg-green-700 font-semibold rounded-lg shadow-md transition duration-200">
          Create Account
        </button>
      </div>
    </form>

    <!-- Login Link -->
    <div class="text-center text-sm mt-4 text-gray-400">
      Already have an account?
      <a href="${pageContext.request.contextPath}/client/login" class="text-green-400 hover:underline">Login here</a>
    </div>

    <p class="text-center text-gray-500 text-xs mt-6">AutoCare – User Registration Portal</p>
  </div>

  <!-- Password Validation Script -->
  <script>
    const passwordInput = document.getElementById("password");

    const lengthHint = document.getElementById("lengthHint");
    const uppercaseHint = document.getElementById("uppercaseHint");
    const lowercaseHint = document.getElementById("lowercaseHint");
    const digitHint = document.getElementById("digitHint");

    passwordInput.addEventListener("input", function () {
      const val = passwordInput.value;

      lengthHint.classList.toggle("text-green-600", val.length >= 8);
      lengthHint.classList.toggle("text-red-600", val.length < 8);

      uppercaseHint.classList.toggle("text-green-600", /[A-Z]/.test(val));
      uppercaseHint.classList.toggle("text-red-600", !/[A-Z]/.test(val));

      lowercaseHint.classList.toggle("text-green-600", /[a-z]/.test(val));
      lowercaseHint.classList.toggle("text-red-600", !/[a-z]/.test(val));

      digitHint.classList.toggle("text-green-600", /\d/.test(val));
      digitHint.classList.toggle("text-red-600", !/\d/.test(val));
    });
  </script>

</body>
</html>