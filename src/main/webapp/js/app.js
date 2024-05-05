document.addEventListener('DOMContentLoaded', function() {
    fetchPosts();

    document.getElementById("new-post-btn").addEventListener("click", function() {
        toggleFormDisplay('post-form');
    });

    document.getElementById("post-form").addEventListener('submit', function(e) {
        e.preventDefault();
        const title = document.getElementById('post-title').value;
        const author = document.getElementById('post-author').value;
        const content = document.getElementById('post-content').value;
        const post = { title, author, content };

        fetch('/api/posts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(post)
        }).then(response => response.json())
            .then(post => {
                addPostElements(post);
                document.getElementById('post-title').value = '';
                document.getElementById('post-author').value = '';
                document.getElementById('post-content').value = '';
                document.getElementById("post-form").style.display = "none";  // Hide form after submission
                fetchPosts(); // Refresh the list of posts
            });
    });
});

function fetchPosts() {
    fetch('/api/posts', { method: 'GET' })
        .then(response => response.json())
        .then(posts => {
            const postLinks = document.getElementById('post-links');
            postLinks.innerHTML = '';  // Clear previous posts
            posts.forEach(post => addPostElements(post));
        });
}

function addPostElements(post) {
    const postLinks = document.getElementById('post-links');
    const postDiv = document.createElement('div');
    const postTitle = document.createElement('h3');
    postTitle.innerText = post.title;
    postTitle.style.cursor = 'pointer';
    postTitle.onclick = function() { showPostDetail(post.id); };
    const postAuthor = document.createElement('div');
    postAuthor.innerText = 'Author: ' + post.author;
    const postPreviewContent = document.createElement('div');
    postPreviewContent.innerText = post.content.substring(0, 100) + '...';
    postDiv.appendChild(postTitle);
    postDiv.appendChild(postAuthor);
    postDiv.appendChild(postPreviewContent);
    postLinks.appendChild(postDiv);
}

function showPostDetail(postId) {
    fetch(`/api/posts/${postId}`, { method: 'GET' })
        .then(response => response.json())
        .then(post => {
            const detailView = document.getElementById('post-detail');
            detailView.style.display = 'block';
            document.getElementById('detail-title').innerText = post.title;
            document.getElementById('detail-author').innerText = 'By ' + post.author;
            document.getElementById('detail-content').innerText = post.content;

            // Display existing comments
            const commentsContainer = document.getElementById('comments');
            commentsContainer.innerHTML = '';
            if (post.comments) {
                post.comments.forEach(comment => {
                    const commentDiv = document.createElement('div');
                    commentDiv.innerHTML = `<strong>${comment.author}</strong>: ${comment.content}`;
                    commentsContainer.appendChild(commentDiv);
                });
            }

            // Prepare the comment form
            const commentForm = document.getElementById('comment-form');
            commentForm.style.display = 'block'; // Make sure the comment form is visible
            commentForm.onsubmit = function(e) {
                e.preventDefault(); // Prevent the default form submission
                submitComment(postId);
            };
        });
}

function submitComment(postId) {
    const author = document.getElementById('comment-author').value;
    const content = document.getElementById('comment-content').value;
    const comment = { author, content };

    fetch(`/api/posts/${postId}/comments`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(comment)
    }).then(response => response.json())
        .then(comment => {
            document.getElementById('comments').innerHTML += `<div><strong>${comment.author}</strong>: ${comment.content}</div>`;
            document.getElementById('comment-author').value = '';
            document.getElementById('comment-content').value = '';
        });
}


function toggleFormDisplay(formId) {
    const form = document.getElementById(formId);
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
}
