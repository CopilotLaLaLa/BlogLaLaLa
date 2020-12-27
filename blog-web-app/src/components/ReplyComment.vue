<template>
    <div class="comment-urp">
      <img class="avatar-s" src="../assets/image/user2.jpg" alt="">
      <div class="textarea-container">
        <textarea name="msg" id="1060" cols="80" rows="50" placeholder="发条友善的评论" class="ipt-txt">
        </textarea>
        <button type="submit" class="comment-submit" disabled="disabled">发表评论</button>
      </div>
    </div>
</template>

<script>
export default {
  name: 'ReplyComment',
  props: {
    msg: String
  }
}
</script>


<style scoped>

</style>
