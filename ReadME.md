![develop](https://github.com/karthyks/Composed/actions/workflows/develop-workflow.yml/badge.svg) ![release](https://img.shields.io/github/v/release/karthyks/Composed?display_name=tag) [![CircleCI](https://dl.circleci.com/status-badge/img/gh/karthyks/Composed/tree/develop.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/karthyks/Composed/tree/develop)
# Composed
Utility library for Jetpack compose projects.

## Overlay
Render a composable dynamically on top of a composable. Overlay is a stack based renderer, multiple composables can be rendered on top of each other.
One of the main use-cases is it is really useful in a modularised project where you don't have to expose the UI code but just the overlay contract and also to render condition based composable (show a dialog on click/ show a message on top of a screen).

## Installation
```
implementation "io.github.karthyks:composed:{latest_version}"
```

## Usage
```
class FullScreenDialog : Overlay {
    override val viewHolder: ViewHolder by viewHolder { disposable ->
        RenderFullScreenDialogContent {
            disposable.dispose()
        }
    }
}

```
https://user-images.githubusercontent.com/4476587/213263411-1450e338-d704-4dea-ae89-07c1d6e65825.mp4
