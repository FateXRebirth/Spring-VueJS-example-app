<template>
  <div>
    <Carousel/>
    <Modal/>
    <NavMenu/>
  </div>
</template>

<script>
import Carousel from '~/components/Carousel.vue';
import Modal from '~/components/Modal.vue';
import NavMenu from '~/components/NavMenu.vue';

export default {
  components: {
    Carousel,
    Modal,
    NavMenu
  },
}
</script>