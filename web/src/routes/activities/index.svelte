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
            <a href="/activities/{activity.id}" class="flex justify-between items-center p-2">
                {#if activity.logoUrl}
                    <img class="w-16 h-16 mr-4" src={activity.logoUrl} alt="Логотип" />
                {/if}
                <p class="flex-1 underline text-center text-l">{activity.name}</p>
            </a>
        {/each}
    </div>
{/if}
