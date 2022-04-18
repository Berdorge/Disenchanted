<script lang="ts" context="module">
    import { ActivityApi } from "$lib/activity/ActivityApi"
    import { StatusCodes } from "http-status-codes"
    import { container } from "tsyringe"
    import type { Load } from "./[id]"

    export const load: Load = async ({ params }) => {
        const api = container.resolve(ActivityApi)
        const activity = await api.getActivity(params.id)

        if (activity === null) {
            return { status: StatusCodes.NOT_FOUND }
        } else {
            return {
                status: StatusCodes.OK,
                props: { activity }
            }
        }
    }
</script>

<script lang="ts">
    import Universities from "$lib/components/activities/Universities.svelte"
    import Title from "$lib/components/Title.svelte"
    import type { ChildActivity } from "$lib/activity/ChildActivity"
    import ChildActivities from "$lib/components/activities/ChildActivities.svelte"

    export let activity: ChildActivity
</script>

<Title caption={activity.name} />

<div class="max-w-2xl m-auto">
    {#if activity.parent}
        <a class="block underline text-gray-800 p-1" href="/activities/{activity.parent.id}">
            ← {activity.parent.name}
        </a>
    {/if}

    {#if activity.logoUrl}
        <img class="m-auto max-h-32" src={activity.logoUrl} alt="Логотип" />
    {/if}

    <h1 class="text-2xl text-gray-800 text-center p-2">{activity.name}</h1>

    <ChildActivities activityId={activity.id} />

    <Universities activityId={activity.id} />
</div>
