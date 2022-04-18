<script lang="ts">
    import { session } from "$app/stores"
    import HeaderNavLink, { FullColorCondition } from "$lib/components/HeaderNavLink.svelte"
    import "reflect-metadata"
</script>

<header class="shadow-md fixed top-0 left-0 z-10 w-screen h-12">
    <nav class="flex max-w-3xl m-auto">
        <HeaderNavLink title="Главная" href="" fullColorWhen={FullColorCondition.EQUALS} />
        <div class="flex-1" />
        <HeaderNavLink title="Университеты" href="universities" />
        <HeaderNavLink title="Активности" href="activities" />
        <div class="flex-1" />
        {#if $session.user}
            <HeaderNavLink title="Профиль" href="profile" />
        {:else}
            <HeaderNavLink title="Авторизация" href="auth" />
        {/if}
    </nav>
</header>

<div class="pt-12 h-full">
    <slot />
</div>

<style lang="postcss" global>
    @tailwind base;
    @tailwind components;
    @tailwind utilities;

    * {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        font: inherit;
        vertical-align: baseline;
        box-sizing: border-box;
    }

    html,
    body {
        height: 100%;
    }
</style>
