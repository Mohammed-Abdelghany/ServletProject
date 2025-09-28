<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Login - Servlet CRUD</title>
  <link rel="stylesheet"href="${pageContext.request.contextPath}/CrudItemsProject/css/styles.css"/>
</head>
<body>
  <main class="login-page">
    <section class="card">
      <div class="brand">
        <h1>Servlet CRUD</h1>
        <p class="subtitle">Sign in to manage your items</p>
      </div>

      <form class="login-form" action="/LoginServlet" method="post" autocomplete="on">
        <div class="field">
          <label for="email">Email</label>
          <input id="email" name="email" type="email" placeholder="you@example.com" required />
        </div>

        <div class="field">
          <label for="password">Password</label>
          <div class="password-wrapper">
            <input id="password" name="password" type="password" placeholder="••••••••" required />
            <button type="button" class="toggle" aria-label="Toggle password">Show</button>
          </div>
        </div>

        <div class="row">
          <label class="checkbox">
            <input type="checkbox" name="remember" />
            <span>Remember me</span>
          </label>
          <a class="small-link" href="/register">Create account</a>
        </div>

        <button class="btn primary" type="submit">Sign In</button>

        <div class="separator">or</div>

        <p class="foot">Forgot password? <a href="/forgot">Reset it</a></p>
      </form>
    </section>
  </main>

  <script>
    // Toggle password visibility
    document.addEventListener('click', function (e) {
      if (e.target.matches('.toggle')) {
        const wrapper = e.target.closest('.password-wrapper');
        const input = wrapper.querySelector('input');
        if (input.type === 'password') {
          input.type = 'text';
          e.target.textContent = 'Hide';
        } else {
          input.type = 'password';
          e.target.textContent = 'Show';
        }
      }
    });
  </script>
</body>
</html>
    