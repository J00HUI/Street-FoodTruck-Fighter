<template>
  <router-view></router-view>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif, ;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100vw;
  height: 100vh;
  max-width: 600px;
  min-width: 320px;
  border: 5px solid var(--color-gray-1);
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
  --color-gray-2: #898989;
  --color-yellow-1: #fdeca6;
  --color-yellow-2: #ffdd2d;
  --color-beige-2: #fff6dd;
  --color-beige-2: #fff1d6;
  --color-orange-1: #f6ab1c;
  --color-orange-2: #f2be5c;
  --color-purple-1: #f7e8f7;
  --color-purple-2: #d076cd;
  --color-blue-1: #beefff;
  --color-blue-2: #00c2ff;
  --color-pink-1: #ffd4d4;
  --color-pink-2: #ffaeae;
  --color-green-1: #7fd488;
}
h1 {
  margin: 0px;
  /* 나중에 지울 것 */
}
</style>
