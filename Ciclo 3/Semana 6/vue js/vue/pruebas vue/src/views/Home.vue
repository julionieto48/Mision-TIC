<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>

    <div id="contenido">
      <hr>
      <input type="checkbox" v-model="variable_a">
      <p v-if="{variable_a} === false"> Si buenas</p>

      <hr>
      <button v-on:click="agradecer">Agradecer</button>

    </div>
  </div>
  
</template>

<script>
export default {

  name: "home",
  methods: {
    agradecer(){
      console.log("bienvenido y gracias")
      alert("Gracias arigato sisas")
    },
    click(){
      variable_a = false;
    }
  },

}
</script>



<style>
.home {
  display: flex;
  justify-content: center;
  align-items: center;
}
#contenido{
  position: relative;
  bottom: 150px ;
}
</style>




