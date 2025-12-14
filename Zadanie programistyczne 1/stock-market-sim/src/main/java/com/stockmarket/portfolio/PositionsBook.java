package com.stockmarket.portfolio;

import java.util.ArrayList;

import com.stockmarket.exceptions.AssetNotFoundInPositionsException;

import com.stockmarket.domain.Asset;

/**
* Klasa Holdings reprezentuję cały zestaw akcji posiadanych w portfolio
*/

public class PositionsBook {

    private final ArrayList<Position> positions;

    public PositionsBook() {
        this.positions = new ArrayList<Position>();
    }

    public void addPosition(Asset asset, int quantity) {
        asset = validateAsset(asset);
        Position position = findPosition(asset);
        if (position == null) {
            positions.add(new Position(asset, quantity));
        } else {
            position.addQuantity(quantity);
        }
    }

    public void reducePosition(Asset asset, int quantity) throws AssetNotFoundInPositionsException {
        asset = validateAsset(asset);
        Position position = findPosition(asset);

        if (position == null) {
            throw new AssetNotFoundInPositionsException("Nie można zmniejszyć ilości akcji którch nie ma w portlefu.");
        }
        
        int PositionQuantity = position.getQuantity();

        if (PositionQuantity == quantity){
            positions.remove(position);
        } else if (PositionQuantity < quantity) {
            throw new IllegalArgumentException("Nie można usunąć większej ilości akcji niż jest w portfelu.");
        } else {
            position.reduceQuantity(quantity);
        }
    }

    public Position getPosition(Asset asset) throws AssetNotFoundInPositionsException {
        asset = validateAsset(asset);
        Position position = findPosition(asset);
        if (position == null) {
            throw new AssetNotFoundInPositionsException("Nie znaleziono podanych akcji w portfelu.");
        }
        return position;
    }

    private Position findPosition(Asset assetToFind) {
        for (Position position : positions) {
            if (position.getAsset().equals(assetToFind)) {
                return position;
            }
        }
        return null;
    }

    private Asset validateAsset(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("Oczekiwano instancji Asset, przekazano null.");
        }
        return asset;
    }

}
