<template>
<div>
  <div class="ui attached animate__animated animate__fadeIn" v-for="item  in list"
       @click="toBlogDetails(item.id)">
    <div class="d-web-info-box" data-wow-duration="0.4s" style="cursor: pointer;">
      <div class="ui mobile stackable grid">
        <div class="seven wide column" style="overflow: hidden;">
          <div style="border-radius: 15px;overflow: hidden">
            <img class="ui rounded image centered Itemimg" :src="item.firstPicture"></div>
        </div>
        <div class="nine wide column h-padded-td-large">
          <a href="javascript:;"><h3 class="ui header titleSuspension">{{ item.title }}</h3></a>
          <p class="m-text">{{ item.description }}</p>
          <div class="ui grid">
            <div class="wide column">
              <div class="ui mini horizontal link list">
                <div class="item">
                  <div class="content">
                    <span class="header"><i class="user outline red  icon"></i>{{ item.userName }}</span>
                  </div>
                </div>
                <div class="item">
                  <i class="clock outline blue icon"></i>
                  {{ item.edittime != null && item.edittime != '' ? item.edittime : item.createtime }}
                </div>
                <div class="item">
                  <i class="eye green icon"></i> {{ item.views }}
                </div>
                <div class="item">
                  <i class="comment alternate outline orange icon"></i> {{ item.commentCount }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export  default {
  name:'blogList',
  props:['list'],
  methods:{
    toBlogDetails(id) {
      this.$router.push({
        name: 'blog',
        query: {
          blogId: id
        }
      })
    }
  }
}
</script>
