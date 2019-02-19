console.log("Vue is running...")
var dashboard = new Vue({
    el: '#Dashboard',
    data : {
      tab: "",
    },
    mounted(){
        this.fetchPosts();
        this.tab = 'car';
    },
    methods: {
        fetchPosts(){
            axios.get("/posts").then(function(response){
                this.posts = response.data;
            }.bind(this));
        },
        getLink(post){
            return '/post/' + post.id;
        },
        Switch(tab) {
          this.tab = tab;
        }
    },
    watch: {
      tab: function(newValue, oldValue) {
        console.log(newValue);
      }
    }
});