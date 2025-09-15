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

module.exports = router;
