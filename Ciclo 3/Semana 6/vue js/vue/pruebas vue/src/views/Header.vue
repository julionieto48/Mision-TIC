<template>
   <header>
    <h2> HOLA !!! </h2>

        <div id = "nav">
            <router-link to="/"> Home  </router-link>
            <router-link to="/about"> About </router-link>
        </div>
        <div id = "nav">
            <button v-on:click="activar"> Presioname, se algo sobre ti...</button>
        </div>

        <p v-if="{variable}"> Variable es Verdadera</p>
        <p v-if="{variable} === false"> Su nombre es {{nombre}} y edad es {{edad}}</p>
        <hr>

        <input v-model="job"  placeholder="trabajo a aplicar!!"/>
        <p> Trabajo deseado: {{job}}</p>
        
    </header>
</template>

<script>
export default {
    name: "Header",
    data: function() {
        return{
            variable : true,
            nombre : "Julian",
            edad : 18,

        }
    },
    activar :function(){
        variable : false;
    }
}
</script>





<style scoped>

#nav{
  background: rgb(151,176,243);
}

</style>