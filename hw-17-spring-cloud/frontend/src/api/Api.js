import axios from "axios";

export const Api = {
    getBooks() {
        return axios.get('http://localhost:8085/books');
    },

    deleteBook(id) {
        return axios.delete('http://localhost:8085/books/' + id);
    },

    addBook(bookData) {
        return axios.post('http://localhost:8085/books', bookData);
    },

    editBook(id, bookData) {
        return axios.put('http://localhost:8085/books/' + id, bookData);
    },

    getBook(id) {
        return axios.get('http://localhost:8085/books/' + id);
    },

    addComment(commentData) {
        return axios.post('http://localhost:8085/comment/', commentData)
    },

    deleteComment(id) {
        return axios.delete('http://localhost:8085/comment/' + id);
    }
};