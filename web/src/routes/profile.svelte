<script lang="ts">
    import { goto } from "$app/navigation"
    import { session } from "$app/stores"
    import Button from "$lib/components/Button.svelte"
    import Title from "$lib/components/Title.svelte"
    import { COOKIE_JWT } from "$lib/core/cookies"
    import Cookies from "js-cookie"

    function logout() {
        Cookies.remove(COOKIE_JWT)
        session.update((session) => {
            delete session.user
            return session
        })
        goto("/")
    }
</script>

<Title caption="Профиль" />

<p>Вас зовут {$session.user?.username}</p>

<Button on:click={logout}>Можно и выйти...</Button>
