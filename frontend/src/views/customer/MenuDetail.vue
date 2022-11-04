<template>
    <Header></Header>
    <div class="container">
        <div class="top">
            <div class="pictureContainer">
                <img class="picture" src="@/assets/hamburger.svg" alt />
            </div>
            <div class="amount">
                <button @click="minusAmount">-</button>
                    <div id="amount">
                        <p>12</p>
                    </div>
                <button @click="plusAmount">+</button>
            </div>
        </div>

         <div class="cardContainer">
            <div class="card">
                <div class="menuName">
                    <h3>[140g 소고기패티]시그니처버거</h3>
                </div>
                <div class="detail">
                    <p2>버터향 가득한 수제번과 델리랩 특제소스와 치즈피클 양파까아지</p2>
                </div>
            </div>
        </div>

        <button class="payButton">
            <p>7500원 담기</p>
        </button>
    </div>
</template>

<script>
import Header from '@/components/customer/BackButtonHeader.vue'
export default {
    components: {
        Header,
    },
    setup() {
        function minusAmount() {
            
        }
        function plusAmount() {
            
        }
        return{
            minusAmount,
            plusAmount,
        }
    }   
}
</script>

<style scoped>

.container{
    position: relative;
    height: 100%;
}
/***************** top style start ******************/
.top{
    background-color: #FFF5DC;
    height: 20rem;
    display: flex;
    justify-content: center;
    flex-direction: column;
}
.pictureContainer{
    height: 80%;

}
.picture{
    width: 100%;
    height: 100%;
}
.amount{
    display: flex;
    align-items: center;
    justify-content: center;
    margin-left: 1rem;
    padding: 3px;
    background-color: #FFF09F;
    border: 1.5px solid #FFDD2D;
    border-radius: 1.5rem;
    height: 1.8rem;
    width: 4.5rem;
}
button{
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
    width: 1.1rem;
    border: 0;
    border-radius: 100%;
    box-shadow: 1px 3px 5px -3px grey;
    font-weight: bold;
    color : gray;
}
p{
    margin-left: 0.3rem;
    margin-right: 0.3rem;
}
/***************** top style end ******************/

/***************** card style start ******************/
.cardContainer{
    height: 10rem;
    display: flex;
    justify-content: center;
    margin-top: 1rem;
    margin-bottom: 1.5rem;
}
.card{
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 21.5rem;

    height: auto;
    border-radius: 1rem;
    box-shadow: 2px 3px 15px -6px gray;
}
.menuName{
    height: 20%;
    margin-top: 1rem;
}
.detail{
    display: flex;
    height: 80%;
    margin-top: 2rem;
    justify-content: center;
}
p2{
    font-family: 'SCoreDream';
    width: 70%;
    display: -webkit-box;
    overflow: hidden;
    word-break: break-all;
    word-wrap: break-word;
    text-overflow: ellipsis;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    text-align: center;
}
/***************** card style end ******************/

.payButton{
    position: absolute;
    bottom: 8rem;
    right: 4rem;
    width: 70%;
    height: 3.5rem;
    background-color: #FFE060;
    border-radius: 2rem;
    box-shadow: 2px 3px 15px -6px grey;
    font-weight: bold;
    font-family: 'SCoreDream';
    text-align: center;
}

</style>