export const validateEmail = (email) => {

    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    return pattern.test(email);

};

export const validatePassword = (password) => {

    return password.length >= 6;

};

export const validateMobile = (mobile) => {

    return /^[6-9]\d{9}$/.test(mobile);

};