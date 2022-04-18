<script lang="ts" context="module">
    import { ActivityApi } from "$lib/activity/ActivityApi"

    import type { Load } from "@sveltejs/kit"
    import { StatusCodes } from "http-status-codes"
    import { container } from "tsyringe"

    export const load: Load = async () => {
        const api = container.resolve(ActivityApi)
        const activities = await api.getActivities()

        return {
            status: StatusCodes.OK,
            props: { activities }
        }
    }
</script>

<script lang="ts">
    import type { Activity } from "$lib/activity/Activity"
    import Title from "$lib/components/Title.svelte"
    import { empty } from "$lib/utils/Array"

    export let activities: Array<Activity>
</script>

<Title caption="Активности" />

{#if empty(activities)}
    <p class="text-center text-xl text-gray-800 p-2">Университетов нет :(</p>
{:else}
    <div class="max-w-2xl m-auto">
        {#each activities as activity}
            <a href="/activities/{activity.id}" class="block underline text-center text-l p-2">
                {activity.name}
            </a>
        {/each}
    </div>
{/if}
