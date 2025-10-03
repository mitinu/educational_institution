<template>

</template>

<script>
export default {
  name: "moveDiv",
  data() {
    return {
      positionX: 0,
      positionY: 0,
      savePositionX: 0,
      savePositionY: 0,
      isDragging: false,
      startX: 0,
      startY: 0,
      startPosX: 0,
      startPosY: 0,
      fullScreen: false,

    }
  },
  methods: {
    swapShapeScreen(){
      if (this.fullScreen) {
        this.histogram.style.width =" 950px"
        this.histogram.style.height =" 540px"
        this.positionX = this.savePositionX
        this.positionY = this.savePositionY
      }
      else{

        this.histogram.style.width = "100%"
        this.histogram.style.height = `${window.innerHeight}px`

        this.savePositionX = this.positionX
        this.savePositionY = this.positionY

        this.positionX = 0
        this.positionY = 0
      }
      this.fullScreen = !this.fullScreen
    },

    startDrag(event) {
      if (!this.fullScreen ) {
        this.isDragging = true;
        this.startX = event.clientX - this.positionX;
        this.startY = event.clientY - this.positionY;

        document.addEventListener('mousemove', this.onDrag);
        document.addEventListener('mouseup', this.stopDrag);
        document.addEventListener('touchmove', this.onDrag);
        document.addEventListener('touchend', this.stopDrag);
      }
    },
    onDrag(event) {
      if (!this.isDragging) return;

      const clientX = event.touches?.[0]?.clientX ?? event.clientX;
      const clientY = event.touches?.[0]?.clientY ?? event.clientY;

      this.positionX = clientX - this.startX;
      this.positionY = clientY - this.startY;
    },
    stopDrag() {
      this.isDragging = false;
      document.removeEventListener('mousemove', this.onDrag);
      document.removeEventListener('mouseup', this.stopDrag);
      document.removeEventListener('touchmove', this.onDrag);
      document.removeEventListener('touchend', this.stopDrag);
    },

  }
}
</script>

<style scoped>

</style>
