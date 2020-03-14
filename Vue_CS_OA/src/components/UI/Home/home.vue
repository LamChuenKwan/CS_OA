<template>
  <div id="homediv">
    <homeheader/>
    <router-view></router-view>
    <homefooter/>
  </div>
</template>

<script>
import homeheader from './baseheader'
import homefooter from './basefooter'

export default {
  name: 'home',
  components: {homeheader,homefooter},
  data () {
    return {
    }
  }
}
</script>

<style scoped>

</style>


