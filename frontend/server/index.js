const { Nuxt, Builder } = require('nuxt')
const Koa = require('koa')
const session = require('koa-session');
const Router = require('koa-router');
const bodyParser = require('koa-bodyparser');
const logger = require('koa-logger');

const app = new Koa()
const router = new Router();
const host = process.env.HOST || '127.0.0.1'
const port = process.env.PORT || 3000

app.keys = ['secret', 'key'];

const CONFIG = {
  key: 'koa:sess', /** (string) cookie key (default is koa:sess) */
  /** (number || 'session') maxAge in ms (default is 1 days) */
  /** 'session' will result in a cookie that expires when session/browser is closed */
  /** Warning: If a session cookie is stolen, this cookie will never expire */
  maxAge: 86400000,
  overwrite: true, /** (boolean) can overwrite or not (default true) */
  httpOnly: true, /** (boolean) httpOnly or not (default true) */
  signed: true, /** (boolean) signed or not (default true) */
  rolling: false, /** (boolean) Force a session identifier cookie to be set on every response. The expiration is reset to the original maxAge, resetting the expiration countdown. (default is false) */
  renew: false, /** (boolean) renew session when session is nearly expired, so we can always keep user logged in. (default is false)*/
};

app.use(logger())
app.use(bodyParser());
app.use(router.routes())
app.use(router.allowedMethods());
app.use(session(CONFIG, app));
// or if you prefer all default config, just use => app.use(session(app));

// Import and Set Nuxt.js options
let config = require('../nuxt.config.js')
config.dev = !(app.env === 'production')

async function start() {
  // Instantiate nuxt.js
  const nuxt = new Nuxt(config)

  // Build in development
  if (config.dev) {
    const builder = new Builder(nuxt)
    await builder.build()
  }

  app.use(ctx => {
    ctx.status = 200 // koa defaults to 404 when it sees that status is unset
    ctx.body = ctx.request.body;
    return new Promise((resolve, reject) => {
      ctx.res.on('close', resolve)
      ctx.res.on('finish', resolve)
      nuxt.render(ctx.req, ctx.res, promise => {
        // nuxt.render passes a rejected promise into callback on error.
        promise.then(resolve).catch(reject)
      })
    })
  })

  app.listen(port, host)
  console.log('Server listening on http://' + host + ':' + port) // eslint-disable-line no-console
}

start()

router.post('/login', (ctx, next) => {
  const data =  ctx.request.body
  let returnCode;
  if(data.username == 'admin' && data.password == 'admin') {
    returnCode = 1;
    ctx.session.authUser = { 
      username: data.username,
      password: data.password
    }
  } else {
    returnCode = 0;
  }
  ctx.body = {
    "returnCode": returnCode
  }
});

router.get('/logout', (ctx, next) => {
  ctx.session = null;
  ctx.redirect('/');
});