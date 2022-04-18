<script lang="ts">
    import { session } from "$app/stores"
    import Button from "$lib/components/Button.svelte"
    import { AuthApi } from "$lib/auth/AuthApi"
    import { COOKIE_JWT } from "$lib/core/cookies"
    import Cookies from "js-cookie"
    import { createEventDispatcher } from "svelte"
    import { container } from "tsyringe"

    export let username: string

    const dispatch = createEventDispatcher()
    const api = container.resolve(AuthApi)
    let password: string = ""
    let error: string = ""
    let info: string = ""

    $: submitEnabled = Boolean(password) && !error

    function submit() {
        info = "Проверяем..."
        api.signIn({ username, password })
            .then((response) => {
                Cookies.set(COOKIE_JWT, response.jwt)
                session.update((session) => {
                    session.user = response
                    return session
                })
                dispatch("success")
            })
            .catch((e) => (error = e))
            .then(() => (info = ""))
    }

    function passwordChanged() {
        error = ""
    }

    function cancel() {
        dispatch("cancel")
    }
</script>

<p class="text-2xl font-bold inline-block p-2 hover:cursor-pointer" on:click={cancel}>←</p>
<p class="text-center text-xl mb-4 w-full">
    Давно не виделись, {username}! Не забыли свой пароль?
</p>
<input
    type="password"
    autocomplete="current-password"
    bind:value={password}
    on:input={passwordChanged}
    placeholder="Пароль"
    class="block w-full border-2 border-solid rounded-sm border-gray-500 min-w-[10rem] p-2 mb-4"
/>
{#if error}
    <p class="text-center text-sm text-red-600">{error}</p>
{/if}
<div class="text-right">
    {#if info}
        <span class="text-gray-500">{info}</span>
    {:else}
        <Button enabled={submitEnabled} on:click={submit}>Далее</Button>
    {/if}
</div>
