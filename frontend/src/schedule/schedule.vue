<!-- Schedule.vue -->
<template>
  <div class="schedule-container">
    <h1 class="schedule-title">Расписание</h1>
    <select-group
      :selectGroupId="selectGroupId"
      :groups="groups"
      @selectGroup="selectGroup"
      class="group-selector"
    />
    <visualization
      v-if="selectGroupId"
      :scedules="scedules"
      class="schedule-visualization"
    />
  </div>
</template>

<script>
import selectGroup from './components/selectGroup.vue'
import visualization from './components/visualization.vue'

export default {
  name: 'schedule',
  components: {
    selectGroup,
    visualization
  },
  data() {
    return {
      groups: [],
      scedules: [],
      selectGroupId: null
    }
  },
  created() {
    fetch('/api/getGroups')
    .then(res=>{return res.json()})
    .then(data=>{
      this.groups = data
    })
  },
  methods: {
    selectGroup(id) {
      let testData;
      console.log(id)
      this.selectGroupId = id
      fetch(`/api/getScedulesGroup/${id}`)
      .then(res=>{return res.json()})
      .then(data=>{
        console.log(data)
        for (const sceduleKey in data) {
          const scedule = data[sceduleKey]
          this.scedules[sceduleKey] = {
            date: scedule.date,
            couples: scedule.listCouple
          }
        }
        console.log()
      })
      // testData = {
      //   scedules: {
      //     0: {
      //       id: 1,
      //       couples: {
      //         3: {subjectTitle: "Информатика", professorTitle: "Сидоров С.С.", officeTitle: "Комп. класс 3", number: "3"},
      //         4: {subjectTitle: "Химия", professorTitle: "Васильев В.В.", officeTitle: "Лаб. 12", number: "4"},
      //         5: {subjectTitle: "Литература", professorTitle: "Николаева Н.Н.", officeTitle: "Ауд. 304", number: "5"}
      //       },
      //       date: "01.09.2025"
      //     },
      //     1: {
      //       id: 2,
      //       couples: {
      //         1: {subjectTitle: "Иностранный язык", professorTitle: "Смирнова С.С.", officeTitle: "Ауд. 105", number: "1"},
      //         2: {subjectTitle: "Биология", professorTitle: "Кузнецов К.К.", officeTitle: "Лаб. 5", number: "2"},
      //         3: {subjectTitle: "География", professorTitle: "Орлова О.О.", officeTitle: "Ауд. 210", number: "3"}
      //       },
      //       date: "02.09.2025"
      //     },
      //     2: {
      //       id: 3,
      //       couples: {
      //         4: {subjectTitle: "Физкультура", professorTitle: "Тихонов Т.Т.", officeTitle: "Спортзал", number: "4"},
      //         5: {subjectTitle: "Обществознание",professorTitle: "Павлов П.П.",officeTitle: "Ауд. 302",number: "5"},
      //         6: {subjectTitle: "Экономика", professorTitle: "Федоров Ф.Ф.", officeTitle: "Ауд. 401", number: "6"}
      //       },
      //       date: "03.09.2025"
      //     }
      //   }
      // }
      // this.scedules = testData.scedules
    }
  }
}
</script>

<style scoped>
.schedule-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Arial', sans-serif;
}

.schedule-title {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 30px;
  font-size: 2rem;
}

.group-selector {
  margin-bottom: 30px;
}

.schedule-visualization {
  margin-top: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
</style>
