package com.dragonslayer99.todo.utils;

import java.util.HashSet;
import java.util.Set;

public class UserStatus {

    public final static HashSet<String> completedStatus = new HashSet<>(Set.of(
            "done", "completed", "finished", "resolved", "closed", "finalized", "achieved", "accomplished",
            "mark as done", "mark complete", "task completed", "set to done", "close task", "finish task",
            "complete task", "all done", "done with it", "wrapped up", "taken care of", "sorted", "that’s done",
            "finished it", "knocked it out", "handled", "checked off", "signed off", "task was completed",
            "task is done", "issue resolved", "work finished", "assignment completed", "nailed it", "crushed it",
            "shipped", "shipped it", "good to go", "complete", "true", "success"));

    public final static HashSet<String> inCompleteStatus = new HashSet<>(Set.of(
            "not done", "incomplete", "unfinished", "pending", "open", "unresolved", "uncompleted", "in progress",
            "working on it", "ongoing", "under way", "actively working", "still working", "processing", "blocked",
            "stuck", "halted", "paused", "on hold", "waiting", "waiting on someone", "waiting for approval",
            "waiting for input", "postponed", "deferred", "delayed", "rescheduled", "pushed back", "moved to later",
            "backlog", "pending review", "under review", "awaiting review", "needs approval", "awaiting approval",
            "needs verification", "pending validation", "partially done", "partially complete", "almost done",
            "near completion", "in final stages", "not yet", "still pending", "not finished yet", "getting there",
            "halfway done", "working through it", "failed", "error", "needs retry", "retrying", "unsuccessful",
            "not started", "in_progress", "on_hold", "review"));

    public final static HashSet<String> cannotCompleteStatus = new HashSet<>(Set.of(
            "cancelled", "canceled", "aborted", "stopped", "terminated", "dropped", "impossible", "not possible",
            "cannot be done", "infeasible", "unachievable", "not achievable", "permanently blocked", "blocked forever",
            "hard blocked", "deadlocked", "invalid", "obsolete", "deprecated", "no longer needed", "no longer required",
            "irrelevant", "failed permanently", "failed irrecoverably", "unrecoverable failure", "fatal error",
            "rejected", "denied", "disapproved", "declined", "expired", "timed out", "deadline missed", "skipped",
            "bypassed", "omitted", "won’t fix", "cannot reproduce", "out of scope", "duplicate", "invalid request"));

}
