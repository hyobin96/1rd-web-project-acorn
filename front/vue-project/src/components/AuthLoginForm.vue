<template>
    <div>
        <h2>LoginForm</h2>
        <div :class="isIdFocus ? 'input_item_id_focus' : 'input_item_id'">
            <input type="text" id="id" name="id" 
            @focus="isIdFocus = true" @blur="isIdFocus = false" v-model="id" autocomplete="off">
            <label for="id" :class="{floated: isIdFocus || id}">아이디</label></div>
        <div :class="isPwFocus ? 'input_item_pw_focus' : 'input_item_pw'">
            <input type="password" id="pw" name="pw" 
            @focus="isPwFocus = true" @blur="isPwFocus = false" v-model="pw" autocomplete="off">
            <label for="pw" :class="{floated: isPwFocus || pw}">비밀번호</label></div>
        <br>
        <!-- <button class="btn btn-success">로그인</button> -->
    </div>
</template>

<script setup>
import {ref} from 'vue';
const isIdFocus = ref(false)
const isPwFocus = ref(false)

const id = ref('');
const pw = ref('');
</script>

<style scoped>
.input_item_id,
.input_item_pw,
.input_item_id_focus,
.input_item_pw_focus {
  position: relative;
  margin-bottom: 22px;
  border: 1.5px solid #ddd;
  border-radius: 8px;
  background: #fafafa;
  transition: border-color 0.24s;
  min-height: 52px;
}

.input_item_id_focus,
.input_item_pw_focus {
  border-color: #22c55e;
}

.input_item_id input,
.input_item_pw input,
.input_item_id_focus input,
.input_item_pw_focus input {
  width: 100%;
  border: none;
  background: transparent;
  font-size: 16px;
  padding: 20px 12px 8px 12px; /* 위쪽 padding 넉넉 */
  height: 48px;
  outline: none;
  box-sizing: border-box;
}

.input_item_id label,
.input_item_pw label,
.input_item_id_focus label,
.input_item_pw_focus label {
  position: absolute;
  left: 14px;
  top: 50%;
  transform: translateY(-50%);
  color: #bbb;
  font-size: 16px;
  pointer-events: none;
  background: transparent;
  transition: 
    top 0.22s cubic-bezier(.4,0,.2,1),
    font-size 0.22s cubic-bezier(.4,0,.2,1),
    color 0.22s cubic-bezier(.4,0,.2,1),
    background 0.22s cubic-bezier(.4,0,.2,1);
  padding: 0 4px;
  z-index: 2;
}

.input_item_id label.floated,
.input_item_pw label.floated,
.input_item_id_focus label.floated,
.input_item_pw_focus label.floated {
  top: 8px;
  transform: none;
  font-size: 12px;
  color: #22c55e;
  background: #fafafa;
  padding: 0 2px;
}
</style>