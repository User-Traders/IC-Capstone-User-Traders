import http from "@/utils/http";


function userTokenValid(token) {
    return new Promise((resolve, reject) => {
        http.process("user", "userValid", { token: token })
            .then(response => {
                resolve(response)
            })
            .catch(error => {
                reject(error)
            })
    })
}
export {
    userTokenValid,
}