var express = require('express');
var router = express.Router();
var router = express.Router();
var axios = require('axios');
var bodyParser = require('body-parser');

router.use(bodyParser.json());
router.use(bodyParser.urlencoded({extended: true}));

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/about', function(req, res, next) {
  res.render('about', { title: 'Express' });
})

router.get('/contact', function(req, res, next) {
  res.render('contact', { title: 'Express' });
})

router.get('/service', function(req, res, next) {
  res.render('service', { title: 'Express' });
})
// login module
router.get('/login', function(req, res, next) {
  res.render('login', { title: 'Express' });
})
router.get('/login-landlord', function(req, res, next) {
  res.render('login-landlord', { title: 'Express' });
})

router.get('/login-tenant', function(req, res, next) {
  res.render('login-tenant', { title: 'Express' });
})
// register module
router.get('/register', function(req, res, next) {
  res.render('register', { title: 'Express' });
})

router.get('/register-landlord', function(req, res, next) {
  res.render('register-landlord', { title: 'Express' });
})

router.get('/register-tenant', function(req, res, next) {
  res.render('register-tenant', { title: 'Express' });
})
// handling of errors

const handleError = (err, res) => {
  console.error('API Error:', err.message);
  if (err.response) {
    console.error('API Response:', err.response.data);
  }
  res.status(err.status || 500).send(err.message || 'Error processing request');
};
// submission of registration of landlord

const API_REGISTRATION_LANDLORD = "http:localhost:8083/api/v1/landlord_login/register";
router.post('/submit-register-landlord', async (req, res) => {
  try {
    await axios.post(API_REGISTRATION_LANDLORD, req.body);
    res.redirect('/');
  } catch (err) {
    handleError(err, res);
  }
});
// registration for tenants
const API_REGISTRATION_TENANTS="http:localhost:8087/api/v1/tenant_login/register";
router.post('/submit-register-tenant', async (req, res) => {
  try {
    await axios.post(API_REGISTRATION_TENANTS, req.body);
    res.redirect('/');
  } catch (err) {
    handleError(err, res);
  }
});

// login of landlords
const API_LOGIN_LANDLORDS = "http:localhost:8083/api/v1/landlord_login/login";
app.post('/submit-login-landlord', async (req, res) => {
  const { email, password } = req.body;
  const resp = await fetch(API_LOGIN_LANDLORDS, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({ email, password }),
    credentials: 'include'
  });
  const json = await resp.json();
  if (resp.ok) {
    // redirect to OTP page
    res.render('otp-landlord', { email });
  } else {
    res.render('login', { error: json.error || 'Login failed' });
  }
});

// OTP verification landlords

API_OTP_LANDLORDS="http:localhost:8083/api/v1/landlord_login/verify-otp";
app.post('/submit-verify-otp-landlord', async (req, res) => {
  const { code } = req.body;
  const resp = await fetch(API_OTP_LANDLORDS, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({ code }),
    credentials: 'include'
  });
  const json = await resp.json();
  if (resp.ok) {
    res.render('dashboard-landlord', { email: json.email || 'You' });
  } else {
    res.render('otp-landlord', { error: json.error });
  }
});

// otp - resend landlords

API_OTPRESEND_LANDLORD="http:localhost:8083/api/v1/landlord_login/resend-otp";
app.post('/resend-otp-landlord', async (req, res) => {
  const resp = await fetch(API_OTPRESEND_LANDLORD, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    credentials: 'include'
  });
  const json = await resp.json();
  res.json({ ok: resp.ok, ...json });
});

// logout landlord

API_LOGOUT_LANDLORD="http:localhost:8083/api/v1/landlord_login/logout";
app.post('/logout-landlord', async (req, res) => {
  await fetch(API_LOGOUT_LANDLORD, { method: 'POST', credentials: 'include' });
  res.redirect('/');
});

// login of tenant
const API_LOGIN_TENANT = "http:localhost:8087/api/v1/tenant_login/login";
app.post('/submit-login-tenant', async (req, res) => {
  const { email, password } = req.body;
  const resp = await fetch(API_LOGIN_TENANT, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({ email, password }),
    credentials: 'include'
  });
  const json = await resp.json();
  if (resp.ok) {
    // redirect to OTP page
    res.render('otp-tenant', { email });
  } else {
    res.render('login', { error: json.error || 'Login failed' });
  }
});

// OTP verification tenants

API_OTP_TENANT="http:localhost:8087/api/v1/tenant_login/verify-otp";
app.post('/submit-verify-otp-tenant', async (req, res) => {
  const { code } = req.body;
  const resp = await fetch(API_OTP_TENANT, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({ code }),
    credentials: 'include'
  });
  const json = await resp.json();
  if (resp.ok) {
    res.render('dashboard-tenant', { email: json.email || 'You' });
  } else {
    res.render('otp-tenant', { error: json.error });
  }
});

// otp - resend tenants

API_OTPRESEND_TENANT="http:localhost:8087/api/v1/tenant_login/resend-otp";
app.post('/resend-otp-tenant', async (req, res) => {
  const resp = await fetch(API_OTPRESEND_TENANT, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    credentials: 'include'
  });
  const json = await resp.json();
  res.json({ ok: resp.ok, ...json });
});

// logout tenants

API_LOGOUT_TENANT="http:localhost:8087/api/v1/tenant_login/logout";
app.post('/logout-tenant', async (req, res) => {
  await fetch(API_LOGOUT_TENANT, { method: 'POST', credentials: 'include' });
  res.redirect('/');
});

module.exports = router;
