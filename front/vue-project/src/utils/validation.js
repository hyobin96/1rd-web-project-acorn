// 회원가입 시 입력들의 유효성 검사를 위한 유틸 js
import axios from 'axios'
const API_URL = 'http://localhost:8080/api/users'
// 아이디 유효성 검사
export const validateId = async (username) => {
    if (!username) {
        return '아이디를 입력해주세요';
    }
    if (username.includes(' ')) {
        return '공백은 사용할 수 없습니다';
    }
    if (!/^[a-z0-9_]*$/.test(username)) {
        return '소문자, 숫자, 밑줄(_)만 사용할 수 있습니다';
    }
    if (username.length < 5 || username.length > 15) {
        return '아이디는 5~20자여야 합니다';
    }
    //중복 검사
    let message = ''
    await axios.get(
        `${API_URL}/check-username`,
        {
            params: { username }
        }).then(response => {
            console.dir(response)
            message = response.data.message
        }).catch(err => {
            console.dir(err)
            message = err.response.data.message
        })
    //에러 없을 경우 사용 가능한 닉네임입니다가 반환
    return message
}

// 패스워드 유효성 검사
export const validatePassword = (password) => {
    if (!password) {
        return '패스워드를 입력해주세요.'
    }
    if (password.includes(' ')) {
        return '공백은 사용할 수 없습니다.'
    }
    if (password.length < 8 || password.length > 16) {
        return '패스워드는 8~16자여야 합니다.'
    }
    //에러 없음
    return '유효한 비밀번호입니다.'
}

export const validateConfirmPassword = (password, confirmPassword) => {
    if (password !== confirmPassword) {
        return '비밀번호가 일치하지 않습니다.'
    }
    return '비밀번호가 일치합니다.'
}

//닉네임 유효성 검사
export const validateNickname = async (nickname) => {
    if (!nickname) {
        return {
            message: '닉네임을 입력해주세요.',
            success: false,
        }
    }
    if (nickname.includes(' ')) {
        return {
            message: '공백은 사용할 수 없습니다.',
            success: false,
        }
    }
    if (nickname.length < 2 || nickname > 10) {
        return {
            message: '닉네임은 2~10자여야 합니다.',
            success: false,
        }
    }
    //중복 검사
    let message = ''
    await axios.get(
        `${API_URL}/check-nickname`,
        {
            params: { nickname }
        }).then(response => {
            console.dir(response)
            message = response.data.message
        }).catch(err => {
            console.dir(err)
            message = err.response.data.message
        })
    //에러 없을 경우 사용 가능한 닉네임입니다가 반환
    return {
        message,
        success: false,
    }
}

//이메일 유효성 검사
export const validateEmail = async (email) => {
    if (!email) {
        return '이메일을 입력해주세요.'
    }
    if (email.includes(' ')) {
        return '공백은 사용할 수 없습니다.'
    }
    if (!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(email)) {
        return '이메일 형식이 올바르지 않습니다.'
    }
    //중복 검사
    let message = ''
    await axios.get(
        `${API_URL}/check-email`,
        {
            params: { email }
        }).then(response => {
            console.dir(response)
            message = response.data.message
        }).catch(err => {
            console.dir(err)
            message = err.response.data.message
        })
    //에러 없을 경우 사용 가능한 이메일입니다가 반환
    return message
} 