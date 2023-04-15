<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      博客管理系统
    </div>
    <br>
    <div>
      欢迎关注SuanCai的Github站账号: <a href="https://github.com/rage-suancai">https://github.com/rage-suancai</a>   SuanCai
    </div>
    <br>
    <div>
      欢迎
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
