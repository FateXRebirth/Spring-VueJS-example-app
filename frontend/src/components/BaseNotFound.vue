<template>
  <div class="wrapper">
      <div class="error">404</div>
       <div class="navigation"><router-link to="/index" class="link">Click here to go back index...</router-link></div>
  </div>
</template>

<script>
export default {
  name: 'NotFound'
}
</script>

<style >
.wrapper {
    color: white;
    text-align: center;
    background-color: #2D72D9;
    height: 100%;
    width: 100%;
    position: absolute;
}
.error {
    position: absolute;
    height: 200px;
    width: 500px;
    top: 50%;
    left: 50%;
    font-size: 200px;
    margin-top: -150px;
    margin-left: -250px;
}
.navigation {
    position: absolute;
    bottom: 100px;
    width: 500px;
    height: 100px;
    margin-left: -250px;
    left: 50%;
    font-size: 30px;
}
.link {
    color: white;
    text-decoration: unset;
}
.link:hover {
    color: lightseagreen;
}
</style>
