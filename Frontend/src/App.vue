<template>
  <div id="app">
    <div>
      <router-view /> <!-- the vue component will display here -->
    </div>
  </div>
</template>

<script>

export default {
  name: "app"
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
