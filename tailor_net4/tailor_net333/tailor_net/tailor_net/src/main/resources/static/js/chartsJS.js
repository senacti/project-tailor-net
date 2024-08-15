const ctx1 = document.getElementById('chart-1');

  new Chart(ctx1, {
    type: 'polarArea',
    data: {
      labels: ['Camisetas', 'Trajes', 'Perfumes', 'Pantalones'],
      datasets: [{
        label: '# of Votes',
        data: [600, 800, 1000, 550],
        backgroundColor:[
            "rgba(255,99,132,0.7)",
            "rgba(54,162,235,0.7)",
            "rgba(255,206,86,0.7)",
            "rgba(75,192,192,0.7)",
            "rgba(153,102,255,0.2)",
            "rgba(255,159,54,0.2)",
        ],
        borderColor:[
            "rgba(255,99,132,1)",
            "rgba(54,162,235,1)",
            "rgba(255,206,86,1)",
            "rgba(75,192,192,1)",
            "rgba(153,102,255,1)",
            "rgba(255,159,54,1)",
        ],
        borderWidth: 1
      }]
    },
    options: {
        responsive: true,
    }
  });
const ctx2 = document.getElementById('chart-2');

  new Chart(ctx2, {
    type: 'bar',
    data: {
      labels: ['Camisetas', 'Trajes', 'Perfumes', 'Pantalones', 'Vestidos'],
      datasets: [{
        label: 'Compras',
        data: [600, 800, 1000, 550, 700],
        backgroundColor:[
            "rgba(255,99,132,0.5)",
            "rgba(54,162,235,0.5)",
            "rgba(255,206,86,0.5)",
            "rgba(75,192,192,0.5)",
            "rgba(153,102,255,0.5)",
            "rgba(255,159,54,0.2)",
        ],
        borderColor:[
            "rgba(255,99,132,1)",
            "rgba(54,162,235,1)",
            "rgba(255,206,86,1)",
            "rgba(75,192,192,1)",
            "rgba(153,102,255,1)",
            "rgba(255,159,54,1)",
        ],
        borderWidth: 1
      }]
    },
    options: {
        responsive: true,
    }
  });