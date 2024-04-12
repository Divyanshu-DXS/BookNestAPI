function getAllBooks() {
    fetch('/books')
        .then(response => response.json())
        .then(data => displayBooks(data))
        .catch(error => console.error('Error:', error));
}

function displayBooks(books) {
    const bookTable = document.getElementById('bookTable');
    bookTable.innerHTML = '';
    books.forEach(book => {
        const row = `
            <tr>
                <td>${book.bookID}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>
                    <button class="btn btn-info btn-sm" onclick="getBookById(${book.bookID})">Get</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteBookById(${book.bookID})">Delete</button>
                </td>
            </tr>
        `;
        bookTable.innerHTML += row;
    });
}

function getBookById(bookId) {
    fetch(`/books/${bookId}`)
        .then(response => response.json())
        .then(data => alert(`Book ID: ${data.bookID}\nTitle: ${data.title}\nAuthor: ${data.author}`))
        .catch(error => console.error('Error:', error));
}

function deleteBookById(bookId) {
    if (confirm("Are you sure you want to delete this book?")) {
        fetch(`/books/${bookId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert("Book deleted successfully!");
                getAllBooks(); // Refresh the book list after deletion
            } else {
                throw new Error('Failed to delete book');
            }
        })
        .catch(error => console.error('Error:', error));
    }
}


document.getElementById('addBookForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    const formData = new FormData(this);
    const newBook = {};
    formData.forEach((value, key) => {
        newBook[key] = value;
    });
    fetch('/books', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newBook)
    })
    .then(response => response.json())
    .then(data => {
        alert("Book added successfully!");
        getAllBooks(); // Refresh the book list after adding a new book
    })
    .catch(error => console.error('Error:', error));
});
