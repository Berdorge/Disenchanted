<script lang="ts">
    import Button from "$lib/components/Button.svelte"
    import { UserApi } from "$lib/core/user/UserApi"
    import { createEventDispatcher } from "svelte"
    import { container } from "tsyringe"

    const dispatch = createEventDispatcher()
    const api = container.resolve(UserApi)
    let username: string = ""
    let error: string = ""
    let info: string = ""

    $: submitEnabled = !error && /^\w+$/.test(username)

    function submit() {
        info = "Проверяем..."
        api.doesUserExist(username)
            .then((exists) => dispatch(exists ? "signIn" : "signUp", username))
            .catch((e) => (error = e))
            .then(() => (info = ""))
    }

    function usernameChanged() {
        error = ""
    }
</script>

<p class="text-center text-xl mb-4 w-full">
    Введите свой логин, а мы проверим, зарегистрированы ли Вы у нас.
</p>
<input
    type="text"
    bind:value={username}
    on:input={usernameChanged}
    placeholder="Логин"
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
