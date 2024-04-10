<%-- 
    Document   : Technician_Administrator
    Created on : 2024年4月10日, 上午11:09:28
    Author     : hohin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Technician (Administrator)</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Work+Sans:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet">
    <style>
        body {
            font-family: 'Work Sans', sans-serif;
        }

        .section__container {
            padding: 5rem 1rem;
            max-width: var(--max-width);
            margin: auto;
        }

        .section__title {
            font-size: 2rem;
            font-weight: 500;
            color: var(--primary-color);
            margin-bottom: 1rem;
            text-align: center;
        }

        .section__subtitle {
            font-size: 1rem;
            font-style: italic;
            color: var(--primary-color);
            margin-bottom: 5rem;
            text-align: center;
        }

        .cards-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            gap: 1rem;
            padding: 0 20rem;

        }

        .card {
            background: #fff;

            border-radius: 10px;

            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);

            width: calc(25% - 1rem);

            margin-bottom: 1rem;

            padding: 1rem;

            box-sizing: border-box;

            display: flex;

            flex-direction: column;

            justify-content: center;

            text-align: center;

            color: #1dc9cb;
            transition: transform 0.3s ease, box-shadow 0.3s ease;

        }

        .card-title {
            font-size: 1.5rem;
            margin-bottom: .5rem;

        }

        .icon {

            display: block;

            margin: 0 auto 1rem;

            font-size: 50px;

        }

        .card:hover {
            background: linear-gradient(90deg, #369898, #5eeebb);

            color: #fff;

            cursor: pointer;
            transform: scale(1.05);

            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);

        }

        .card:hover .material-icons {
            color: #fff;

        }

        .material-icons {
            font-size: 50px;
        }
    </style>
</head>

<body>



    <h2 class="section__title">Welcome, [Username]!</h2>
    <p class="section__subtitle">Technician (Administrator)</p>


    <div class="cards-container">

        <div class="card">
            <span class="material-icons">
                sentiment_satisfied_alt
            </span>
            <h3 class="card-title">Title</h3>

        </div>

        <div class="card">
            <h3 class="card-title">Title</h3>

        </div>
        <div class="card">
            <h3 class="card-title">Title</h3>

        </div>
        <div class="card">
            <h3 class="card-title">Title</h3>

        </div>
        <div class="card">
            <span class="material-icons">
                sentiment_satisfied_alt
            </span>
            <h3 class="card-title">Title</h3>

        </div>
    </div>
</body>

</html>