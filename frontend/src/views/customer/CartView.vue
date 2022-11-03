<template>
<div class="a">
<Header></Header>
    <div class="container">    
        <div class="cardContainer">
            <div class="card">
                <div>
                    <img class="picture" src="@/assets/hamburger.svg" alt />
                </div>
                <div class="detail">
                    <div class="storeName">
                        <h3>맛있는 햄버거</h3>
                    </div>
                    <div class="orders">
                        <h4>7500원</h4>
                    </div>
                </div>
                

                <div class="delete">
                    <img class="deleteButton" src="@/assets/CloseButton.svg" alt />
                </div>
                <div class="amount">
                    <button>-</button>
                    <div id="amount">
                        <p>12</p>
                    </div>
                    <button>+</button>
                </div>
                 
            </div>
        </div>

    </div>
    <button class="payButton">
        <p>7500원 결제하기</p>
    </button>
        </div>
</template>

<script>
import Header from '@/components/customer/BackButtonHeader.vue'
export default {
    components:{
        Header,
    },
    setup(){
       

        return{

        };
    }
}
</script>

<style scoped>

/***************** card style start ******************/
.cardContainer{
    height: 100%;
    display: flex;
    justify-content: center;
    margin-top: 1rem;
    margin-bottom: 1.5rem;
}
.card{
    position: relative;
    display: flex;
    flex-direction: row;
    align-items: center;
    width: 21.5rem;

    height: auto;
    border: 0.1rem solid lightgray;
    border-radius: 1rem;
    box-shadow: 2px 3px 15px -6px gray;
}

.picture{
    margin-left: 1rem;
}
.detail{
    margin-left: 0.5rem;
}

.delete{
    position: absolute;
    top: 0.7rem;
    right: 0.7rem;
}

.amount{
    display: relative;
    position: absolute;
    display: flex;
    direction: row;
    align-items: center;
    justify-content: center;
    bottom: 0.7rem;
    right: 0.7rem;
    padding: 3px;
    border: 1.5px solid #FFE060;
    border-radius: 0.9rem;
    height: 1.5rem;
    width: 4rem;
}
#amount{
    margin-left: 0.2rem;
    margin-right: 0.2rem;
}

button{
    background-color: transparent;
    border: 0;
}
/***************** card style end ******************/


.payButton{
    position: absolute;
    bottom: 5rem;
    right: 4.3rem;
    width: 70%;
    height: 3.5rem;
    background-color: #FFE060;
    border-radius: 2rem;
    box-shadow: 2px 3px 15px -6px gray;
    font-weight: bold;
    font-family: 'SCoreDream';
}
.a {
    margin: 0;
    padding: 0;
    position: relative;
    width: 100%;
    height: 100%;
}

</style>