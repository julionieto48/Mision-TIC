<template>
  <div>
  <Header />
    <router-view/>
  </div>
  
</template>


<script>
import Header from './views/Header.vue'

export default{
  components: {
    Header,
  }
}
</script>


<style>
#app {
  font-family: "Segoe UI", serif; 
}
</style>
