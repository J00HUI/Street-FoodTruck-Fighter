<template>
  <router-view></router-view>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif, ;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 390px;
  height: 844px;
  margin: 0 auto 0 auto;

}
@font-face {
    font-family: 'SCoreDream';
    font-weight: 500; 
    font-style: normal; 
    src: url(https://cdn.jsdelivr.net/gh/webfontworld/SCoreDream/SCoreDream5.woff2) format('woff2'),
         url(https://cdn.jsdelivr.net/gh/webfontworld/SCoreDream/SCoreDream5.woff) format('woff');
    font-display: swap;
}


:root {
  --color-gray-1: #f7f8f8;
  --color-gray-2: #C8C8C8;
  --color-gray-3: #898989;
  --color-yellow-1: #fdeca6;
  --color-yellow-2: #ffdd2d;
  --color-beige-1: #fff6dd;
  --color-beige-2: #fff1d6;
  --color-orange-1: #f6ab1c;
  --color-orange-2: #f2be5c;
  --color-purple-1: #f7e8f7;
  --color-purple-2: #d076cd;
  --color-blue-1: #beefff;
  --color-blue-2: #00c2ff;
  --color-pink-0: #F7E8F7;
  --color-pink-1: #ffd4d4;
  --color-pink-2: #ffaeae;
  --color-green-1: #7fd488;
}

body {
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none
}
</style>
