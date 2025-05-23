// 회원가입 시 입력들의 유효성 검사를 위한 유틸 js
import axios from '@/api/axios'
// 아이디 유효성 검사
export const validateUsername = async (username) => {
    if (!username) {
        return {
            message: '아이디를 입력해주세요',
            success: false,
        }
    }
    if (username.includes(' ')) {
        return {
            message: '공백은 사용할 수 없습니다',
            success: false,
        }
    }
    if (!/^[a-z0-9_]*$/.test(username)) {
        return {
            message: '소문자, 숫자, 밑줄(_)만 사용할 수 있습니다',
            success: false,
        }
    }
    if (username.length < 5 || username.length > 15) {
        return {
            message: '아이디는 5~20자여야 합니다',
            success: false,
        }
    }
    //중복 검사
    let message = ''
    let success = false
    try {
        const res = await axios.get(
            'users/check-username',
            {
                params: { username }
            }
        )
        //에러 없을 경우 사용 가능한 아이디입니다가 반환
        message = res.data.message
        success = true

    } catch (err) {
        //에러 있을 경우 중복된 아이디입니다. 반환
        console.log(err);
        
        message = err.response.data.message
        
    }

    return { message, success }
}

// 패스워드 유효성 검사
export const validatePassword = (password) => {
    if (!password) {
        return {
            message: '패스워드를 입력해주세요.',
            success: false,
        }
    }
    if (password.includes(' ')) {
        return {
            message: '공백은 사용할 수 없습니다.',
            success: false,
        }
    }
    if (password.length < 8 || password.length > 16) {
        return {
            message: '패스워드는 8~16자여야 합니다.',
            success: false,
        }
    }
    //에러 없음
    return {
        message: '유효한 비밀번호입니다.',
        success: true,
    }
}

//비밀번호 일치 검사
export const validateConfirmPassword = (password, confirmPassword) => {
    if (password !== confirmPassword) {
        return {
            message: '비밀번호가 일치하지 않습니다.',
            success: false,
        }
    }
    return {
        message: '비밀번호가 일치합니다.',
        success: true,
    }
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
    let success = false
    try {
        const res = await axios.get(
            'users/check-nickname',
            {
                params: { nickname }
            })
        message = res.data.message
        success = true
    } catch (err) {
        message = err.response.data.message
    }

    //에러 없을 경우 사용 가능한 닉네임입니다가 반환
    return { message, success }
}

//이메일 유효성 검사
export const validateEmail = async (email) => {
    if (!email) {
        return {
            message: '이메일을 입력해주세요.',
            success: false,
        }
    }
    if (email.includes(' ')) {
        return {
            message: '공백은 사용할 수 없습니다.',
            success: false,
        }
    }
    if (!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(email)) {
        return {
            message: '이메일 형식이 올바르지 않습니다.',
            success: false,
        }
    }
    //중복 검사
    let message = ''
    let success = false
    try {
        const res = await axios.get(
            'users/check-email',
            {
                params: { email }
            })
        message = res.data.message
        success = true
    } catch (err) {
        message = err.response.data.message
    }
    //에러 없을 경우 사용 가능한 이메일입니다가 반환
    return { message, success, }
} 